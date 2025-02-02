/* eslint-disable no-unused-vars */
// src/components/Auth/Signup.jsx
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Button from "@/components/ui/Button";
import Input from "@/components/ui/Input";
import { signup } from '@/services/api';

const Signup = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    amsUserFname: '',
    amsUserLname: '',
    amsUsername: '',
    amsPassword: ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await signup(formData);
      alert('User registered successfully!');
      console.log(response);
      navigate('/login');
    } catch (error) {
      alert('Error registering user.');
      console.error(error);
    }
  };

  return (
    <div className="max-w-md mx-auto p-4 border rounded-xl shadow-md mt-10">
      <h2 className="text-2xl font-bold mb-4">Register User</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <Input 
          type="text" 
          name="amsUserFname" 
          placeholder="First Name" 
          value={formData.amsUserFname} 
          onChange={handleChange} 
          required 
        />
        <Input 
          type="text" 
          name="amsUserLname" 
          placeholder="Last Name" 
          value={formData.amsUserLname} 
          onChange={handleChange} 
          required 
        />
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
        <Button type="submit" className="w-full">Register</Button>
      </form>
    </div>
  );
};

export default Signup;
