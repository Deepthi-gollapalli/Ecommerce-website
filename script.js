async function loadProducts() {
  const res = await fetch("http://localhost:8080/ecommerce/ProductServlet");
  const products = await res.json();
  const container = document.getElementById("product-list");
  container.innerHTML = products.map(p => `
    <div class="product">
      <img src="${p.imageUrl}" alt="${p.name}">
      <h3>${p.name}</h3>
      <p>${p.description}</p>
      <p><b>₹${p.price}</b></p>
      <button onclick="addToCart(${p.id})">Add to Cart</button>
    </div>
  `).join('');
}
function addToCart(id) {
  fetch(`http://localhost:8080/ecommerce/CartServlet?action=add&id=${id}`);
  alert("Product added to cart!");
}
window.onload = loadProducts;
