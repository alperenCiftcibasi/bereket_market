import React from 'react';
import Header from '../components/Header';
import { useCart } from '../context/CartContext';

function CartPage() {
  const { cartItems, decreaseQuantity, removeFromCart, addToCart } = useCart();

  const handleQuantityChange = (id, delta) => {
    if (delta > 0) {
      // Artır
      const product = cartItems.find(item => item.id === id);
      if (product) {
        addToCart(product);
      }
    } else {
      // Azalt
      decreaseQuantity(id);
    }
  };

  const totalPrice = cartItems.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  return (
    <>
      <Header />
      {cartItems.length === 0 ? (
        <div style={{ padding: '2rem' }}>
          <h1>Sepetiniz Boş</h1>
          <p>Alışverişe devam etmek için ana sayfaya gidin.</p>
        </div>
      ) : (
        <div style={{ padding: '2rem', maxWidth: '800px', margin: '0 auto' }}>
          <h1>Sepetiniz</h1>
          <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '1rem' }}>
            <thead>
              <tr style={{ borderBottom: '1px solid #ccc' }}>
                <th style={{ textAlign: 'left', padding: '0.5rem' }}>Ürün</th>
                <th style={{ padding: '0.5rem' }}>Fiyat</th>
                <th style={{ padding: '0.5rem' }}>Adet</th>
                <th style={{ padding: '0.5rem' }}>Ara Toplam</th>
                <th style={{ padding: '0.5rem' }}>İşlem</th>
              </tr>
            </thead>
            <tbody>
              {cartItems.map(({ id, name, price, quantity, imageUrl }) => (
                <tr key={id} style={{ borderBottom: '1px solid #eee' }}>
                  <td style={{ display: 'flex', alignItems: 'center', gap: '1rem', padding: '0.5rem' }}>
                    <img
                      src={imageUrl}
                      alt={name}
                      style={{ width: '60px', height: '60px', objectFit: 'cover', borderRadius: '8px' }}
                    />
                    <span>{name}</span>
                  </td>
                  <td style={{ textAlign: 'center' }}>{price} ₺</td>
                  <td style={{ textAlign: 'center' }}>
                    <button onClick={() => handleQuantityChange(id, -1)} style={{ marginRight: '0.5rem' }}>-</button>
                    {quantity}
                    <button onClick={() => handleQuantityChange(id, 1)} style={{ marginLeft: '0.5rem' }}>+</button>
                  </td>
                  <td style={{ textAlign: 'center' }}>{price * quantity} ₺</td>
                  <td style={{ textAlign: 'center' }}>
                    <button onClick={() => removeFromCart(id)} style={{ color: 'red' }}>X</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          <h2 style={{ textAlign: 'right', marginTop: '1.5rem' }}>
            Toplam: {totalPrice} ₺
          </h2>
        </div>
      )}
    </>
  );
}

export default CartPage;
