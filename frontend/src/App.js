import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import { AuthProvider } from './context/AuthContext';
import { CartProvider } from './context/CartContext';
import { CategoryProvider } from './context/CategoryContext';
import { UserProvider } from './context/UserContext';

import MainPage from './pages/MainPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import CartPage from './pages/CartPage';
import CategoryPage from './pages/CategoryPage';
import CampaignPage from './pages/CampaignPage';
import OrdersPage from './pages/OrdersPage';
import AddressesPage from './pages/AddressesPage';
import PaymentMethodsPage from './pages/PaymentMethodsPage';

import ProtectedRoute from './components/ProtectedRoute';
import AdminPanel from './pages/AdminPanel';

function App() {
  return (
    <AuthProvider>
      <UserProvider>
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
                  <Route path="/orders" element={<OrdersPage />} />
                  <Route path="/addresses" element={<AddressesPage />} />
                  <Route path="/payment-methods" element={<PaymentMethodsPage />} />
                  
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
      </UserProvider>
    </AuthProvider>
  );
}

export default App;
