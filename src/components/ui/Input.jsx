/* eslint-disable no-unused-vars */
// src/components/ui/Input.jsx
import React from 'react';
import PropTypes from 'prop-types';

const Input = ({
  id,
  type = 'text',
  label,
  placeholder,
  value,
  onChange,
  className = '',
  error,
  required = false,
  disabled = false,
  helperText,
  name,
  onBlur,
  autoComplete,
  maxLength,
}) => {
  const inputId = id || name;

  return (
    <div className="w-full">
      {label && (
        <label
          htmlFor={inputId}
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          {label}
          {required && <span className="text-red-500 ml-1">*</span>}
        </label>
      )}
      <div className="relative">
        <input
          id={inputId}
          type={type}
          name={name}
          placeholder={placeholder}
          value={value}
          onChange={onChange}
          onBlur={onBlur}
          disabled={disabled}
          required={required}
          maxLength={maxLength}
          autoComplete={autoComplete}
          aria-invalid={!!error}
          aria-describedby={error ? `${inputId}-error` : undefined}
          className={`
            w-full p-2 border rounded-lg transition duration-200
            ${error
              ? 'border-red-500 focus:ring-red-500 focus:border-red-500'
              : 'border-gray-300 focus:ring-blue-500 focus:border-blue-500'
            }
            ${disabled ? 'bg-gray-100 cursor-not-allowed' : 'bg-white'}
            focus:outline-none focus:ring-2
            ${className}
          `}
        />
        {error && (
          <div
            id={`${inputId}-error`}
            className="mt-1 text-sm text-red-600"
            role="alert"
          >
            {error}
          </div>
        )}
        {helperText && !error && (
          <div className="mt-1 text-sm text-gray-500">{helperText}</div>
        )}
      </div>
    </div>
  );
};

Input.propTypes = {
  id: PropTypes.string,
  type: PropTypes.oneOf([
    'text',
    'password',
    'email',
    'number',
    'tel',
    'url',
    'search',
    'date',
  ]),
  label: PropTypes.string,
  placeholder: PropTypes.string,
  value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  onChange: PropTypes.func.isRequired,
  className: PropTypes.string,
  error: PropTypes.string,
  required: PropTypes.bool,
  disabled: PropTypes.bool,
  helperText: PropTypes.string,
  name: PropTypes.string,
  onBlur: PropTypes.func,
  autoComplete: PropTypes.string,
  maxLength: PropTypes.number,
};

export default Input;
