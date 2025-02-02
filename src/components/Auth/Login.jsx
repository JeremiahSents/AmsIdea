/* eslint-disable react/no-unescaped-entities */
/* eslint-disable no-unused-vars */
// src/components/Auth/Login.jsx
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Button from "@/components/ui/Button";
import Input from "@/components/ui/Input";
import { login } from "@/services/api";

const Login = () => {
    const [formData, setFormData] = useState({
        amsUsername: '',
        amsPassword: ''
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
        setError(''); // Clear any previous error when user types
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError('');
        
        try {
            const response = await login(formData);
            // You might want to store the token in localStorage or context
            localStorage.setItem('token', response.token);
            navigate('/dashboard'); // Navigate to dashboard after successful login
        } catch (error) {
            setError(error.response?.data?.message || 'Failed to login. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="max-w-md mx-auto p-4 border rounded-xl shadow-md mt-10">
            <h2 className="text-2xl font-bold mb-4">Login</h2>
            {error && (
                <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                    {error}
                </div>
            )}
            <form onSubmit={handleSubmit} className="space-y-4">
                <Input
                    type="text"
                    name="amsUsername"
                    placeholder="Username"
                    value={formData.amsUsername}
                    onChange={handleChange}
                    required
                />
                <Input
                    type="password"
                    name="amsPassword"
                    placeholder="Password"
                    value={formData.amsPassword}
                    onChange={handleChange}
                    required
                />
                <Button 
                    type="submit" 
                    className="w-full"
                    loading={loading}
                    disabled={loading}
                >
                    {loading ? 'Logging in...' : 'Login'}
                </Button>
            </form>
            <p className="mt-4 text-center">
                Don't have an account? <Link to="/signup" className="text-blue-600 hover:underline">Sign up</Link>
            </p>
        </div>
    );
};

export default Login;