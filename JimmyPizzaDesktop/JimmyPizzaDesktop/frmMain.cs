using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Windows.Forms;

namespace JimmyPizzaDesktop
{
    public partial class frmMain : Form
    {
        private string filePath { get; set; } = "";
        private string fileContent { get; set; } = "";

        public frmMain()
        {
            InitializeComponent();
        }

        private void btnSelectFile_Click(object sender, EventArgs e)
        {
            using (OpenFileDialog openFileDialog = new OpenFileDialog()
            {
                DefaultExt = "*.encrypted",
                Filter = "encrypted Jimmy Pizza file (*.encrypted)|*.encrypted",
                FilterIndex = 0,
                Multiselect = false,
                SupportMultiDottedExtensions = false
            })
            {
                if (openFileDialog.ShowDialog() == DialogResult.OK)
                {
                    //Get the path of specified file
                    filePath = openFileDialog.FileName;

                    lblFilePath.Text = filePath;

                    //Read the contents of the file into a stream
                    var fileStream = openFileDialog.OpenFile();

                    using (StreamReader reader = new StreamReader(fileStream))
                    {
                        fileContent = reader.ReadToEnd();
                    }

                    txtResult.Text = fileContent;
                }
                else
                {
                    ResetForm();
                }
            }
        }

        private void btnDecrypt_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(filePath))
            {
                ResetForm();
                MessageBox.Show("Select a file first.");
                return;
            }
            var pizzaOrder = Decrypt(txtKey.Text.Trim(), fileContent);
            if (pizzaOrder == null)
            {
                ResetForm();
                txtResult.Text = string.Format("Could not decrypt the file with key '{0}'", txtKey.Text);
                return;
            }

            txtResult.Text = JsonConvert.SerializeObject(pizzaOrder, Formatting.Indented);
            lblPizzaOrder.Text = string.Format("{0} pizza{1} ordered for a total amount of R {2:#.##}", pizzaOrder.Count, pizzaOrder.Count != 1 ? "s" : "", pizzaOrder.Sum(n => n.Price));
        }

        public List<Pizza> Decrypt(string key, string Encrypted)
        {
            const int pad = 3;
            const string kiv = "P1z5@0rd";

            try
            {
                using (var csp = new DESCryptoServiceProvider() { Key = Encoding.UTF8.GetBytes(key), IV = Encoding.UTF8.GetBytes(kiv) })
                using (var ms = new MemoryStream(Encrypted.Where((n, i) => i % pad == 0).Select((n, i) => Convert.ToByte(Encrypted.Substring(i * pad, Math.Min(Encrypted.Substring(i * pad).Length, pad)))).ToArray()))
                using (var cs = new CryptoStream(ms, csp.CreateDecryptor(), CryptoStreamMode.Read))
                using (var sr = new StreamReader(cs))
                {
                    string json = sr.ReadLine();
                    return JsonConvert.DeserializeObject<List<Pizza>>(json);
                }
            }
            catch (Exception ex)
            {
                Console.Write(ex.Message + " " + (ex.StackTrace ?? "") + "\n\n" + JsonConvert.SerializeObject(new { Encrypted }));
                return null;
            }
        }

        private void ResetForm()
        {
            lblFilePath.Text = "";
            filePath = "";
            fileContent = "";
            txtResult.Text = "";
            lblPizzaOrder.Text = "";
        }
    }
}
