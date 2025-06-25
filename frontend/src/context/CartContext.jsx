import React, { createContext, useContext, useState, useEffect } from 'react';
import { useAuth } from './AuthContext';
import axios from '../api/axiosInstance';

const CartContext = createContext();

export function CartProvider({ children }) {
  const { user } = useAuth(); // Giriş yapmış kullanıcı
  const [cartItems, setCartItems] = useState([]);

  // Kullanıcının sepetini backend’den çek
  const fetchCartItems = async () => {
    if (!user) {
      setCartItems([]);
      return;
    }
    try {
      const response = await axios.get('/api/cart', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      });
      setCartItems(response.data); // response.data: CartItemResponseDto listesi
    } catch (error) {
      console.error('Sepet verisi alınamadı:', error);
      setCartItems([]);
    }
  };

  useEffect(() => {
    fetchCartItems();
  }, [user]);

  // Sepete ürün ekle (backend’e POST)
  const addToCart = async (product) => {
    if (!user) {
      alert('Lütfen önce giriş yapınız.');
      return;
    }
    try {
      await axios.post(
        '/api/cart/add',
        { productId: product.id, quantity: 1 },
        { headers: { Authorization: `Bearer ${localStorage.getItem('token')}` } }
      );
      fetchCartItems();
    } catch (error) {
      console.error('Sepete ekleme başarısız:', error);
    }
  };

  // Ürün adet artır
  const increaseQuantity = async (productId) => {
    const item = cartItems.find(item => item.productId === productId);
    if (!item) return;

    try {
      await axios.put(
        '/api/cart/update',
        null,
        {
          params: { productId, quantity: item.quantity + 1 },
          headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
        }
      );
      fetchCartItems();
    } catch (error) {
      console.error('Adet artırılamadı:', error);
    }
  };

  // Ürün adet azalt
  const decreaseQuantity = async (productId) => {
    const item = cartItems.find(item => item.productId === productId);
    if (!item || item.quantity <= 1) {
      // Eğer adet 1 ise, silme işlemi yap
      removeFromCart(productId);
      return;
    }

    try {
      await axios.put(
        '/api/cart/update',
        null,
        {
          params: { productId, quantity: item.quantity - 1 },
          headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
        }
      );
      fetchCartItems();
    } catch (error) {
      console.error('Adet azaltılamadı:', error);
    }
  };

  // Ürün sepetten sil
  const removeFromCart = async (productId) => {
    try {
      await axios.delete('/api/cart/remove', {
        params: { productId },
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      });
      fetchCartItems();
    } catch (error) {
      console.error('Ürün sepetten silinemedi:', error);
    }
  };

  // Sepeti temizle
  const clearCart = async () => {
    try {
      await axios.delete('/api/cart/clear', {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      });
      fetchCartItems();
    } catch (error) {
      console.error('Sepet temizlenemedi:', error);
    }
  };

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
