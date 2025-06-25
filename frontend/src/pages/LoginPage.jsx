import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';
import { useAuth } from '../context/AuthContext';
import axios from 'axios';

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const { login } = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!email || !password) {
      alert('Lütfen tüm alanları doldurun.');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', {
        email,
        password
      });

      const { token, role } = response.data;

      // AuthContext'e kullanıcı bilgilerini gönder
      login({
        username: email,
        token,
        role
      });

      // Rol bazlı yönlendirme
      if (role === 'ADMIN') {
        navigate('/admin');
      } else {
        navigate('/');
      }

    } catch (error) {
      console.error('Login hatası:', error);
      if (error.response?.data?.message) {
        alert(error.response.data.message);
      } else {
        alert('Giriş başarısız. Lütfen tekrar deneyin.');
      }
    }
  };

  return (
    <>
      <Header />
      <div style={{
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        minHeight: '80vh',
        background: 'linear-gradient(135deg, #6b73ff 0%, #000dff 100%)',
        padding: '2rem'
      }}>
        <form
          onSubmit={handleSubmit}
          style={{
            backgroundColor: 'white',
            padding: '2.5rem',
            borderRadius: '12px',
            boxShadow: '0 8px 24px rgba(0, 0, 0, 0.2)',
            width: '100%',
            maxWidth: '400px'
          }}
        >
          <h2 style={{
            textAlign: 'center',
            marginBottom: '2rem',
            color: '#333',
            fontWeight: '700',
            fontSize: '1.75rem'
          }}>Giriş Yap</h2>

          <label htmlFor="email" style={{ display: 'block', marginBottom: '0.5rem', color: '#555' }}>
            Email
          </label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={e => setEmail(e.target.value)}
            style={{
              width: '100%',
              padding: '0.75rem',
              marginBottom: '1.5rem',
              borderRadius: '8px',
              border: '1px solid #ccc',
              fontSize: '1rem'
            }}
            placeholder="email@example.com"
            required
          />

          <label htmlFor="password" style={{ display: 'block', marginBottom: '0.5rem', color: '#555' }}>
            Şifre
          </label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            style={{
              width: '100%',
              padding: '0.75rem',
              marginBottom: '2rem',
              borderRadius: '8px',
              border: '1px solid #ccc',
              fontSize: '1rem'
            }}
            placeholder="Şifrenizi girin"
            required
          />

          <button
            type="submit"
            style={{
              width: '100%',
              padding: '0.75rem',
              backgroundColor: '#000dff',
              color: 'white',
              fontWeight: '600',
              fontSize: '1.1rem',
              borderRadius: '8px',
              border: 'none',
              cursor: 'pointer',
              transition: 'background-color 0.3s ease'
            }}
            onMouseOver={e => (e.target.style.backgroundColor = '#4c66ff')}
            onMouseOut={e => (e.target.style.backgroundColor = '#000dff')}
          >
            Giriş Yap
          </button>
        </form>
      </div>
    </>
  );
}

export default LoginPage;
