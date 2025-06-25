import React, { useState } from 'react';
import Header from '../components/Header';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';
import { useUser } from '../context/UserContext';

function CartPage() {
  const { cartItems, decreaseQuantity, removeFromCart, increaseQuantity, clearCart } = useCart();
  const { user } = useAuth();
  const { profile, addOrder } = useUser();

  const [confirmVisible, setConfirmVisible] = useState(false);
  const [selectedAddressId, setSelectedAddressId] = useState(null);
  const [selectedCardId, setSelectedCardId] = useState(null);

  const handleQuantityChange = (productId, delta) => {
    if (delta > 0) {
      increaseQuantity(productId);
    } else {
      decreaseQuantity(productId);
    }
  };

  // Toplam fiyatı backend'in gönderdiği totalPrice'dan hesaplamak yerine,
  // frontend de fiyat * adet yapabilirsin ama backend de daha doğru olur
  const totalPrice = cartItems.reduce(
    (sum, item) => sum + item.totalPrice,
    0
  );

  const handleConfirmOrder = () => {
    if (!selectedAddressId) {
      alert('Lütfen bir teslimat adresi seçin.');
      return;
    }
    if (!selectedCardId) {
      alert('Lütfen bir ödeme yöntemi seçin.');
      return;
    }

    const selectedAddress = profile.addresses.find(addr => addr.id === selectedAddressId);
    const selectedCard = profile.cards.find(card => card.id === selectedCardId);

    const newOrder = {
      date: new Date().toISOString().split('T')[0], // YYYY-MM-DD
      total: totalPrice,
      items: cartItems.map(({ productId, productName, price, quantity }) => ({
        id: productId,
        name: productName,
        price,
        quantity,
      })),
      address: selectedAddress,
      paymentMethod: {
        cardHolder: selectedCard.cardHolder,
        number: selectedCard.number,
        expiry: selectedCard.expiry,
      },
    };

    addOrder(newOrder);

    alert('Sipariş onaylandı! Sipariş geçmişinize eklendi.');

    clearCart();
    setConfirmVisible(false);
    setSelectedAddressId(null);
    setSelectedCardId(null);
  };

  if (!user) {
    return (
      <>
        <Header />
        <div style={{ padding: '2rem', textAlign: 'center' }}>
          <h1>Sepet İçin Giriş Yapınız</h1>
          <p>Sepete ürün eklemek için lütfen giriş yapınız.</p>
        </div>
      </>
    );
  }

  if (cartItems.length === 0) {
    return (
      <>
        <Header />
        <div style={{ padding: '2rem', textAlign: 'center' }}>
          <h1>Sepetiniz Boş</h1>
          <p>Alışverişe devam etmek için ana sayfaya gidin.</p>
        </div>
      </>
    );
  }

  return (
    <>
      <Header />
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
            {cartItems.map(({ productId, productName, quantity, price, totalPrice, imageUrl }) => (
              <tr key={productId} style={{ borderBottom: '1px solid #eee' }}>
                <td
                  style={{
                    display: 'flex',
                    alignItems: 'center',
                    gap: '1rem',
                    padding: '0.5rem',
                  }}
                >
                  <img
                    src={imageUrl}
                    alt={productName}
                    style={{
                      width: '60px',
                      height: '60px',
                      objectFit: 'cover',
                      borderRadius: '8px',
                    }}
                  />
                  <span>{productName}</span>
                </td>
                <td style={{ textAlign: 'center' }}>{price} ₺</td>
                <td style={{ textAlign: 'center' }}>
                  <button
                    onClick={() => handleQuantityChange(productId, -1)}
                    style={{ marginRight: '0.5rem' }}
                  >
                    -
                  </button>
                  {quantity}
                  <button
                    onClick={() => handleQuantityChange(productId, 1)}
                    style={{ marginLeft: '0.5rem' }}
                  >
                    +
                  </button>
                </td>
                <td style={{ textAlign: 'center' }}>{totalPrice} ₺</td>
                <td style={{ textAlign: 'center' }}>
                  <button onClick={() => removeFromCart(productId)} style={{ color: 'red' }}>
                    X
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        <h2 style={{ textAlign: 'right', marginTop: '1.5rem' }}>
          Toplam: {totalPrice} ₺
        </h2>

        {/* Sepeti Onayla Butonu */}
        <div style={{ textAlign: 'right', marginTop: '1rem' }}>
          <button
            onClick={() => setConfirmVisible(true)}
            style={{
              padding: '0.75rem 1.5rem',
              backgroundColor: '#007bff',
              color: 'white',
              border: 'none',
              borderRadius: '6px',
              cursor: 'pointer',
              fontWeight: '600',
              fontSize: '1rem',
            }}
          >
            Sepeti Onayla
          </button>
        </div>

        {/* Onay Bölümü */}
        {confirmVisible && (
          <div
            style={{
              marginTop: '2rem',
              padding: '1.5rem',
              border: '1px solid #ccc',
              borderRadius: '8px',
              backgroundColor: '#f9f9f9',
            }}
          >
            <h2>Sipariş Bilgileri</h2>

            <div style={{ marginBottom: '1rem' }}>
              <h3>Adres Seçiniz</h3>
              {(!profile || !profile.addresses || profile.addresses.length === 0) ? (
                <p>Henüz kayıtlı adresiniz yok.</p>
              ) : (
                profile.addresses.map(addr => (
                  <label key={addr.id} style={{ display: 'block', marginBottom: '0.5rem' }}>
                    <input
                      type="radio"
                      name="address"
                      value={addr.id}
                      checked={selectedAddressId === addr.id}
                      onChange={() => setSelectedAddressId(addr.id)}
                      style={{ marginRight: '0.5rem' }}
                    />
                    <strong>{addr.title}</strong> - {addr.detail}
                  </label>
                ))
              )}
            </div>

            <div style={{ marginBottom: '1rem' }}>
              <h3>Ödeme Yöntemi Seçiniz</h3>
              {(!profile || !profile.cards || profile.cards.length === 0) ? (
                <p>Henüz kayıtlı ödeme yöntemi yok.</p>
              ) : (
                profile.cards.map(card => (
                  <label key={card.id} style={{ display: 'block', marginBottom: '0.5rem' }}>
                    <input
                      type="radio"
                      name="paymentMethod"
                      value={card.id}
                      checked={selectedCardId === card.id}
                      onChange={() => setSelectedCardId(card.id)}
                      style={{ marginRight: '0.5rem' }}
                    />
                    <strong>{card.cardHolder}</strong> - {card.number} (Son Kullanma: {card.expiry})
                  </label>
                ))
              )}
            </div>

            <div style={{ marginBottom: '1rem' }}>
              <h3>Sepetteki Ürünler</h3>
              <ul>
                {cartItems.map(item => (
                  <li key={item.productId}>
                    {item.productName} x {item.quantity} — {item.totalPrice} ₺
                  </li>
                ))}
              </ul>
            </div>

            <div style={{ marginBottom: '1rem' }}>
              <h3>Toplam Fiyat: {totalPrice} ₺</h3>
            </div>

            <button
              onClick={handleConfirmOrder}
              style={{
                padding: '0.75rem 1.5rem',
                backgroundColor: '#28a745',
                color: 'white',
                border: 'none',
                borderRadius: '6px',
                cursor: 'pointer',
                fontWeight: '600',
                fontSize: '1rem',
              }}
            >
              Siparişi Onayla
            </button>

            <button
              onClick={() => setConfirmVisible(false)}
              style={{
                marginLeft: '1rem',
                padding: '0.75rem 1.5rem',
                backgroundColor: '#6c757d',
                color: 'white',
                border: 'none',
                borderRadius: '6px',
                cursor: 'pointer',
                fontWeight: '600',
                fontSize: '1rem',
              }}
            >
              İptal
            </button>
          </div>
        )}
      </div>
    </>
  );
}

export default CartPage;
