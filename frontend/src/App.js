import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import { AuthProvider } from './context/AuthContext';
import { CartProvider } from './context/CartContext';
import { CategoryProvider } from './context/CategoryContext';

import MainPage from './pages/MainPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import CartPage from './pages/CartPage';
import CategoryPage from './pages/CategoryPage';
import CampaignPage from './pages/CampaignPage';

import ProtectedRoute from './components/ProtectedRoute';
import AdminPanel from './pages/AdminPanel';

function App() {
  return (
    <AuthProvider>
      <CategoryProvider>
        <CartProvider>
          <Router>
            <Routes>
              <Route path="/" element={<MainPage />} />
              <Route path="/login" element={<LoginPage />} />
              <Route path="/register" element={<RegisterPage />} />
              <Route path="/cart" element={<CartPage />} />
              <Route path="/category/:categoryId" element={<CategoryPage />} />
              <Route path="/campaign/:campaignId" element={<CampaignPage />} />
              
              {/* Admin paneli korumalı rota */}
              <Route
                path="/admin"
                element={
                  <ProtectedRoute requiredRole="admin">
                    <AdminPanel />
                  </ProtectedRoute>
                }
              />

              {/* İleride diğer sayfalar */}
            </Routes>
          </Router>
        </CartProvider>
      </CategoryProvider>
    </AuthProvider>
  );
}

export default App;
