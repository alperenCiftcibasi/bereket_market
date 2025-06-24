import React, { createContext, useContext, useState } from 'react';

// Context oluştur
const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);  // Başlangıçta null

  // Login fonksiyonu (test amaçlı, backend sonrası kolayca güncellenir)
  const login = (userData) => {
    setUser(userData);
  };

  // Logout fonksiyonu
  const logout = () => {
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
