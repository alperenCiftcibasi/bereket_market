import React from 'react';
import Header from '../components/Header';

function AdminPanel() {
  return (
    <>
      <Header />
      <div style={{ padding: '2rem', maxWidth: '900px', margin: '0 auto' }}>
        <h1 style={{ marginBottom: '1.5rem' }}>Admin Paneli</h1>
        <p>Bu sayfa sadece admin kullanıcılar tarafından görüntülenebilir.</p>

        {/* İleride admin işlemleri için bileşenler buraya eklenecek */}

        <section style={{ marginTop: '2rem' }}>
          <h2>Kullanıcı Yönetimi</h2>
          <p>Burada kullanıcı listesi, roller ve izinler yönetilebilir.</p>
        </section>

        <section style={{ marginTop: '2rem' }}>
          <h2>Ürün Yönetimi</h2>
          <p>Yeni ürün ekleme, ürün düzenleme ve silme işlemleri yapılabilir.</p>
        </section>

        <section style={{ marginTop: '2rem' }}>
          <h2>Kampanya Yönetimi</h2>
          <p>İndirim kampanyaları oluşturulup düzenlenebilir.</p>
        </section>
      </div>
    </>
  );
}

export default AdminPanel;
