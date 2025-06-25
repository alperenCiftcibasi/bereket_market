import React, { useEffect, useState } from 'react';
import Header from '../components/Header';
import axios from '../api/axiosInstance';

function AdminPanel() {
  const [users, setUsers] = useState([]);
  const [products, setProducts] = useState([]);
  const [campaigns, setCampaigns] = useState([]);

  useEffect(() => {
    fetchUsers();
    fetchProducts();
    fetchCampaigns();
  }, []);

  const fetchUsers = async () => {
    try {
      const response = await axios.get('/rest/api/users/list');
      setUsers(response.data);
    } catch {
      alert('Kullanıcılar getirilemedi.');
    }
  };

  const fetchProducts = async () => {
    try {
      const response = await axios.get('/api/products/get');
      setProducts(response.data);
    } catch {
      alert('Ürünler getirilemedi.');
    }
  };

  const fetchCampaigns = async () => {
    try {
      const response = await axios.get('/api/admin/campaigns');
      setCampaigns(response.data);
    } catch {
      alert('Kampanyalar getirilemedi.');
    }
  };

  const handleDeleteUser = async (id) => {
    if (window.confirm('Kullanıcı silinsin mi?')) {
      try {
        await axios.delete(`/rest/api/users/delete/${id}`);
        setUsers(users.filter(u => u.id !== id));
      } catch {
        alert('Kullanıcı silinemedi.');
      }
    }
  };

  const handleDeleteProduct = async (id) => {
    if (window.confirm('Ürün silinsin mi?')) {
      try {
        await axios.delete(`/api/products/delete/${id}`);
        setProducts(products.filter(p => p.id !== id));
      } catch {
        alert('Ürün silinemedi.');
      }
    }
  };

  const handleDeleteCampaign = async (id) => {
    if (window.confirm('Kampanya silinsin mi?')) {
      try {
        await axios.delete(`/api/admin/campaigns/${id}`);
        setCampaigns(campaigns.filter(c => c.id !== id));
      } catch {
        alert('Kampanya silinemedi.');
      }
    }
  };

  return (
    <>
      <Header />
      <div style={{ padding: '2rem', maxWidth: '1000px', margin: '0 auto' }}>
        <h1>Admin Paneli</h1>

        {/* Kullanıcı Yönetimi */}
        <section style={{ marginTop: '2rem' }}>
          <h2>Kullanıcı Yönetimi</h2>
          <table style={{ width: '100%', borderCollapse: 'collapse' }}>
            <thead>
              <tr><th>ID</th><th>Email</th><th>Rol</th><th>İşlem</th></tr>
            </thead>
            <tbody>
              {users.map(user => (
                <tr key={user.id}>
                  <td>{user.id}</td><td>{user.email}</td><td>{user.role}</td>
                  <td><button onClick={() => handleDeleteUser(user.id)}>Sil</button></td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>

        {/* Ürün Yönetimi */}
        <section style={{ marginTop: '2rem' }}>
          <h2>Ürün Yönetimi</h2>
          <table style={{ width: '100%', borderCollapse: 'collapse' }}>
            <thead>
              <tr><th>ID</th><th>İsim</th><th>Fiyat</th><th>Kategori</th><th>İşlem</th></tr>
            </thead>
            <tbody>
              {products.map(p => (
                <tr key={p.id}>
                  <td>{p.id}</td><td>{p.name}</td><td>{p.price}₺</td><td>{p.categoryName}</td>
                  <td><button onClick={() => handleDeleteProduct(p.id)}>Sil</button></td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>

        {/* Kampanya Yönetimi */}
        <section style={{ marginTop: '2rem' }}>
          <h2>Kampanya Yönetimi</h2>
          <table style={{ width: '100%', borderCollapse: 'collapse' }}>
            <thead>
              <tr><th>ID</th><th>Başlık</th><th>İndirim Oranı</th><th>Aktif mi?</th><th>İşlem</th></tr>
            </thead>
            <tbody>
              {campaigns.map(c => (
                <tr key={c.id}>
                  <td>{c.id}</td><td>{c.name}</td><td>{c.discountRate}%</td><td>{c.active ? 'Evet' : 'Hayır'}</td>
                  <td><button onClick={() => handleDeleteCampaign(c.id)}>Sil</button></td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      </div>
    </>
  );
}

export default AdminPanel;
