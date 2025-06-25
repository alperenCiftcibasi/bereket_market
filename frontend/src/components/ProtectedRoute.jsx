import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

function ProtectedRoute({ children, requiredRole }) {
  const { user } = useAuth();

  if (!user) {
    return <Navigate to="/login" replace />;
  }

  if (
    requiredRole &&
    user.role &&
    user.role.toLowerCase() !== requiredRole.toLowerCase()
  ) {
    return <Navigate to="/" replace />;
  }

  return children;
}

export default ProtectedRoute;
