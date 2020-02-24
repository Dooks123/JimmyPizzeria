using Newtonsoft.Json;

namespace JimmyPizzaDesktop
{
    public class Pizza
    {
        [JsonProperty("pizzaID")]
        public int PizzaID { get; set; } = 0;
        [JsonProperty("name")]
        public string Name { get; set; } = "";
        [JsonProperty("description")]
        public string Description { get; set; } = "";
        [JsonProperty("price")]
        public double Price { get; set; } = 0;
    }
}
