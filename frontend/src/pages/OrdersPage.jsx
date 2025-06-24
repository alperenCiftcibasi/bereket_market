import React from 'react';
import Header from '../components/Header';
import { useAuth } from '../context/AuthContext';
import { useUser } from '../context/UserContext';

function OrdersPage() {
  const { user } = useAuth();
  const { profile } = useUser();

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

  const orders = profile?.orders || [];

  return (
    <>
      <Header />
      <div style={{ padding: '2rem', maxWidth: '800px', margin: '0 auto' }}>
        <h1>Sipariş Geçmişi</h1>
        {orders.length === 0 ? (
          <p>Henüz siparişiniz yok.</p>
        ) : (
          orders.map(order => (
            <div
              key={order.id}
              style={{
                border: '1px solid #ccc',
                borderRadius: '8px',
                padding: '1rem',
                marginBottom: '1rem',
                backgroundColor: '#fafafa',
              }}
            >
              <h3>Sipariş No: {order.id}</h3>
              <p><strong>Tarih:</strong> {order.date}</p>
              <p><strong>Toplam:</strong> {order.total} ₺</p>

              <table style={{ width: '100%', borderCollapse: 'collapse' }}>
                <thead>
                  <tr style={{ borderBottom: '1px solid #ddd' }}>
                    <th style={{ textAlign: 'left' }}>Ürün</th>
                    <th style={{ textAlign: 'center' }}>Miktar</th>
                    <th style={{ textAlign: 'center' }}>Fiyat</th>
                  </tr>
                </thead>
                <tbody>
                  {order.items.map(item => (
                    <tr key={item.id} style={{ borderBottom: '1px solid #eee' }}>
                      <td>{item.name}</td>
                      <td style={{ textAlign: 'center' }}>{item.quantity}</td>
                      <td style={{ textAlign: 'center' }}>{item.price} ₺</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          ))
        )}
      </div>
    </>
  );
}

export default OrdersPage;
