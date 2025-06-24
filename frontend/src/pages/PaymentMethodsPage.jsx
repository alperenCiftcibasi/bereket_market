import React, { useState } from 'react';
import Header from '../components/Header';
import { useAuth } from '../context/AuthContext';
import { useUser } from '../context/UserContext';

function PaymentMethodsPage() {
  const { user } = useAuth();
  const { profile, addCard, removeCard } = useUser();

  const [newCard, setNewCard] = useState({
    cardHolder: '',
    number: '',
    expiry: '',
    cvc: '',
  });

  if (!user) {
    return (
      <>
        <Header />
        <div style={{ padding: '2rem', textAlign: 'center' }}>
          <h2>Önce giriş yapmalısınız.</h2>
        </div>
      </>
    );
  }

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewCard(prev => ({ ...prev, [name]: value }));
  };

  const handleAddCard = (e) => {
    e.preventDefault();
    const { cardHolder, number, expiry, cvc } = newCard;
    if (!cardHolder.trim() || !number.trim() || !expiry.trim() || !cvc.trim()) {
      alert('Lütfen tüm alanları doldurun.');
      return;
    }
    // Basit kart numarası ve cvc kontrolü yapılabilir
    if (number.replace(/\s+/g, '').length !== 16) {
      alert('Kart numarası 16 hane olmalıdır.');
      return;
    }
    if (cvc.length !== 3) {
      alert('CVC 3 hane olmalıdır.');
      return;
    }
    addCard(newCard);
    setNewCard({ cardHolder: '', number: '', expiry: '', cvc: '' });
  };

  return (
    <>
      <Header />
      <div style={{ padding: '2rem', maxWidth: '600px', margin: '0 auto' }}>
        <h1>Ödeme Yöntemlerim</h1>

        <form onSubmit={handleAddCard} style={{ marginBottom: '2rem' }}>
          <input
            type="text"
            name="cardHolder"
            placeholder="Kart Sahibinin Adı"
            value={newCard.cardHolder}
            onChange={handleInputChange}
            style={{ width: '100%', padding: '0.5rem', marginBottom: '0.5rem' }}
          />
          <input
            type="text"
            name="number"
            placeholder="Kart Numarası (16 hane)"
            value={newCard.number}
            onChange={handleInputChange}
            maxLength={19} // Boşluklarla beraber 19 karakter olabilir
            style={{ width: '100%', padding: '0.5rem', marginBottom: '0.5rem' }}
          />
          <input
            type="text"
            name="expiry"
            placeholder="Son Kullanma Tarihi (AA/YY)"
            value={newCard.expiry}
            onChange={handleInputChange}
            maxLength={5}
            style={{ width: '100%', padding: '0.5rem', marginBottom: '0.5rem' }}
          />
          <input
            type="text"
            name="cvc"
            placeholder="CVC"
            value={newCard.cvc}
            onChange={handleInputChange}
            maxLength={3}
            style={{ width: '100%', padding: '0.5rem', marginBottom: '0.5rem' }}
          />
          <button
            type="submit"
            style={{
              padding: '0.5rem 1rem',
              backgroundColor: '#007bff',
              color: 'white',
              border: 'none',
              borderRadius: '5px',
              cursor: 'pointer'
            }}
          >
            Kart Ekle
          </button>
        </form>

        {!profile || !profile.cards || profile.cards.length === 0 ? (
          <p>Henüz kayıtlı kartınız yok.</p>
        ) : (
          <ul style={{ listStyle: 'none', padding: 0 }}>
            {profile.cards.map(card => (
              <li key={card.id} style={{
                padding: '1rem',
                border: '1px solid #ccc',
                borderRadius: '6px',
                marginBottom: '1rem',
                position: 'relative'
              }}>
                <strong>{card.cardHolder}</strong>
                <p>{card.number.replace(/\d{12}(\d{4})/, '**** **** **** $1')}</p>
                <p>Son Kullanma: {card.expiry}</p>
                <button
                  onClick={() => removeCard(card.id)}
                  style={{
                    position: 'absolute',
                    top: '10px',
                    right: '10px',
                    backgroundColor: 'red',
                    color: 'white',
                    border: 'none',
                    borderRadius: '4px',
                    padding: '0.25rem 0.5rem',
                    cursor: 'pointer'
                  }}
                  title="Kartı Sil"
                >
                  X
                </button>
              </li>
            ))}
          </ul>
        )}
      </div>
    </>
  );
}

export default PaymentMethodsPage;
