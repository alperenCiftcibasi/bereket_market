import React, { createContext, useContext, useState } from 'react';

// Context oluştur
const AuthContext = createContext();

// Provider component
export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);  // {username, role, token...}

  // Login fonksiyonu: Backend’den gelen userData ile state’i günceller
  const login = (userData) => {
    setUser(userData);
    // Örnek: token localStorage’a kaydedilebilir
    localStorage.setItem('token', userData.token);
  };

  // Logout fonksiyonu: State ve localStorage temizlenir
  const logout = () => {
    setUser(null);
    localStorage.removeItem('token');
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

// Hook olarak dışa aktar
export function useAuth() {
  return useContext(AuthContext);
}
