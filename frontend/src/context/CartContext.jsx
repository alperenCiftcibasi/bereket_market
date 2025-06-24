import React, { createContext, useContext, useState, useEffect, useMemo  } from 'react';
import { useAuth } from './AuthContext';

const CartContext = createContext();

export function CartProvider({ children }) {
  const { user } = useAuth(); // Kullanıcı bilgisi
  // Sepetleri kullanıcı bazlı tutmak için obje kullanalım
  const [allCarts, setAllCarts] = useState({}); // { username1: [...items], username2: [...] }

  // Kullanıcının kendi sepetini kolay erişim için
  const cartItems = useMemo(() => {
    return user ? allCarts[user.username] || [] : [];
  }, [allCarts, user]);
  // Sepete ekleme fonksiyonu
  const addToCart = (product) => {
    if (!user) {
      alert('Lütfen önce giriş yapınız.');
      return;
    }
    setAllCarts(prev => {
      const userCart = prev[user.username] || [];
      const productExists = userCart.find(item => item.id === product.id);
      let updatedUserCart;
      if (productExists) {
        updatedUserCart = userCart.map(item =>
          item.id === product.id ? { ...item, quantity: item.quantity + 1 } : item
        );
      } else {
        updatedUserCart = [...userCart, { ...product, quantity: 1 }];
      }
      return { ...prev, [user.username]: updatedUserCart };
    });
  };

  const increaseQuantity = (productId) => {
    if (!user) return;
    setAllCarts(prev => {
      const userCart = prev[user.username] || [];
      const updatedUserCart = userCart.map(item =>
        item.id === productId ? { ...item, quantity: item.quantity + 1 } : item
      );
      return { ...prev, [user.username]: updatedUserCart };
    });
  };

  const decreaseQuantity = (productId) => {
    if (!user) return;
    setAllCarts(prev => {
      const userCart = prev[user.username] || [];
      const updatedUserCart = userCart
        .map(item =>
          item.id === productId ? { ...item, quantity: item.quantity - 1 } : item
        )
        .filter(item => item.quantity > 0);
      return { ...prev, [user.username]: updatedUserCart };
    });
  };

  const removeFromCart = (productId) => {
    if (!user) return;
    setAllCarts(prev => {
      const userCart = prev[user.username] || [];
      const updatedUserCart = userCart.filter(item => item.id !== productId);
      return { ...prev, [user.username]: updatedUserCart };
    });
  };

  const clearCart = () => {
    if (!user) return;
    setAllCarts(prev => {
      const newCarts = { ...prev };
      newCarts[user.username] = [];
      return newCarts;
    });
  };

  // Opsiyonel: Sayfa yenilense bile kullanıcı sepetini localStorage’da tutabilirsin
  useEffect(() => {
    if (user) {
      const storedCart = localStorage.getItem(`cart_${user.username}`);
      if (storedCart) {
        setAllCarts(prev => ({ ...prev, [user.username]: JSON.parse(storedCart) }));
      }
    }
  }, [user]);

  useEffect(() => {
    if (user) {
      localStorage.setItem(`cart_${user.username}`, JSON.stringify(cartItems));
    }
  }, [cartItems, user]);

  return (
    <CartContext.Provider
      value={{
        cartItems,
        addToCart,
        increaseQuantity,
        decreaseQuantity,
        removeFromCart,
        clearCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
}

export function useCart() {
  return useContext(CartContext);
}
