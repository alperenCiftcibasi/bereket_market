import React, { useState } from 'react';
import Header from '../components/Header';
import { useAuth } from '../context/AuthContext';
import { useUser } from '../context/UserContext';

function AddressesPage() {
  const { user } = useAuth();
  const { profile, addAddress, removeAddress } = useUser();

  const [newAddress, setNewAddress] = useState({ title: '', detail: '' });

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
    setNewAddress(prev => ({ ...prev, [name]: value }));
  };

  const handleAddAddress = (e) => {
    e.preventDefault();
    if (!newAddress.title.trim() || !newAddress.detail.trim()) {
      alert('Lütfen tüm alanları doldurun.');
      return;
    }
    addAddress(newAddress);
    setNewAddress({ title: '', detail: '' });
  };

  return (
    <>
      <Header />
      <div style={{ padding: '2rem', maxWidth: '600px', margin: '0 auto' }}>
        <h1>Adreslerim</h1>

        <form onSubmit={handleAddAddress} style={{ marginBottom: '2rem' }}>
          <input
            type="text"
            name="title"
            placeholder="Adres Başlığı (Örn: Ev, İş)"
            value={newAddress.title}
            onChange={handleInputChange}
            style={{ width: '100%', padding: '0.5rem', marginBottom: '0.5rem' }}
          />
          <textarea
            name="detail"
            placeholder="Adres Detayı"
            value={newAddress.detail}
            onChange={handleInputChange}
            rows={3}
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
            Adres Ekle
          </button>
        </form>

        {!profile || !profile.addresses || profile.addresses.length === 0 ? (
          <p>Henüz kayıtlı adresiniz yok.</p>
        ) : (
          <ul style={{ listStyle: 'none', padding: 0 }}>
            {profile.addresses.map(addr => (
              <li key={addr.id} style={{
                padding: '1rem',
                border: '1px solid #ccc',
                borderRadius: '6px',
                marginBottom: '1rem',
                position: 'relative'
              }}>
                <strong>{addr.title}</strong>
                <p>{addr.detail}</p>
                <button
                  onClick={() => removeAddress(addr.id)}
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
                  title="Adresi Sil"
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

export default AddressesPage;
