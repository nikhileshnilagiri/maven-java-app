import React, {
  useState,
  useEffect,
  useMemo,
  useRef,
  useContext,
  createContext
} from "react";
import "./App.css";

const ThemeContext = createContext();

function ThemeButton() {
  const { theme, setTheme } = useContext(ThemeContext);

  const toggleTheme = () => {
    setTheme(theme === "light" ? "dark" : "light");
  };

  return (
    <button onClick={toggleTheme}>
      Toggle {theme === "light" ? "Dark" : "Light"} Theme
    </button>
  );
}

function App() {
  const [products, setProducts] = useState([]);
  const [search, setSearch] = useState("");
  const [theme, setTheme] = useState("light");

  const nameRef = useRef(null);
  const priceRef = useRef(null);

  const addProduct = () => {
    const name = nameRef.current.value.trim();
    const price = parseFloat(priceRef.current.value);

    if (name === "" || isNaN(price) || price <= 0) {
      alert("Please enter a valid product name and price.");
      return;
    }

    const newProduct = {
      id: Date.now(),
      name: name,
      price: price
    };

    setProducts((prevProducts) => [...prevProducts, newProduct]);

    nameRef.current.value = "";
    priceRef.current.value = "";
    nameRef.current.focus();
  };

  const filteredProducts = useMemo(() => {
    return products.filter((product) =>
      product.name.toLowerCase().includes(search.toLowerCase())
    );
  }, [products, search]);

  const totalValue = useMemo(() => {
    return filteredProducts.reduce(
      (total, product) => total + product.price,
      0
    );
  }, [filteredProducts]);

  useEffect(() => {
    document.body.style.backgroundColor =
      theme === "light" ? "#ffffff" : "#333333";

    document.body.style.color =
      theme === "light" ? "#000000" : "#ffffff";

    return () => {
      document.body.style.backgroundColor = "";
      document.body.style.color = "";
    };
  }, [theme]);

  return (
    <ThemeContext.Provider value={{ theme, setTheme }}>
      <div className="container">
        <h1>Inventory Management</h1>

        <input
          type="text"
          placeholder="Search product..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />

        <br />
        <br />

        <input
          type="text"
          ref={nameRef}
          placeholder="Product Name"
        />

        <input
          type="number"
          ref={priceRef}
          placeholder="Price"
        />

        <button onClick={addProduct}>
          Add Product
        </button>

        <br />
        <br />

        <ThemeButton />

        <h3>
          Total Inventory Value: ₹{totalValue.toFixed(2)}
        </h3>

        {filteredProducts.length === 0 ? (
          <p>No products found.</p>
        ) : (
          <ul>
            {filteredProducts.map((product) => (
              <li key={product.id}>
                {product.name} - ₹{product.price.toFixed(2)}
              </li>
            ))}
          </ul>
        )}
      </div>
    </ThemeContext.Provider>
  );
}

export default App;
