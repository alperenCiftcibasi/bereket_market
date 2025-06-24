import React, { createContext, useContext, useState, useEffect } from 'react';
import { useAuth } from './AuthContext';

const UserContext = createContext();

export function UserProvider({ children }) {
  const { user: authUser } = useAuth(); // AuthContext'ten giriş yapmış kullanıcı bilgisi

  const [profile, setProfile] = useState(null);
  // profile örneği: { isim, soyisim, email, addresses: [], cards: [], orders: [] }

  // AuthUser değiştiğinde profile sıfırlanır veya backend’den çekilir (şimdilik fake veri)
  useEffect(() => {
    if (authUser) {
      setProfile({
        isim: authUser.isim,
        soyisim: authUser.soyisim,
        email: authUser.username,
        addresses: [
          { id: 1, title: 'Ev', detail: 'İstanbul, Kadıköy, Örnek Sokak No:12' },
          { id: 2, title: 'İş', detail: 'İstanbul, Şişli, Çalışma Cad. No:34' },
        ],
        cards: [
          { id: 1, cardHolder: 'Ahmet Yılmaz', number: '**** **** **** 1234', expiry: '12/25' },
        ],
        orders: [
          // İstersen başlangıçta boş bırakabilirsin
          // {
          //   id: 'ORD001',
          //   date: '2025-06-20',
          //   total: 250,
          //   items: [
          //     { id: 'p1', name: 'Ürün 1', quantity: 2, price: 50 },
          //     { id: 'p2', name: 'Ürün 2', quantity: 1, price: 150 },
          //   ],
          // },
        ],
      });
    } else {
      setProfile(null);
    }
  }, [authUser]);

  // Adres ekle
  const addAddress = (newAddress) => {
    setProfile(prev => ({
      ...prev,
      addresses: [...(prev?.addresses || []), { id: Date.now(), ...newAddress }],
    }));
  };

  // Adres sil
  const removeAddress = (id) => {
    setProfile(prev => ({
      ...prev,
      addresses: prev.addresses.filter(addr => addr.id !== id),
    }));
  };

  // Kart ekle
  const addCard = (newCard) => {
    setProfile(prev => ({
      ...prev,
      cards: [...(prev?.cards || []), { id: Date.now(), ...newCard }],
    }));
  };

  // Kart sil
  const removeCard = (id) => {
    setProfile(prev => ({
      ...prev,
      cards: prev.cards.filter(card => card.id !== id),
    }));
  };

  // Sipariş ekle
  const addOrder = (newOrder) => {
    setProfile(prev => ({
      ...prev,
      orders: [...(prev?.orders || []), { id: Date.now().toString(), ...newOrder }],
    }));
  };

  return (
    <UserContext.Provider value={{ profile, addAddress, removeAddress, addCard, removeCard, addOrder }}>
      {children}
    </UserContext.Provider>
  );
}

export function useUser() {
  return useContext(UserContext);
}
