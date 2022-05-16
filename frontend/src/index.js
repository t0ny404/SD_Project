import React from 'react';
import ReactDOM from 'react-dom';
import './Css/index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import SignUp from "./Customer/SignUp";
import Cart from "./Customer/Cart";
import Orders from "./Customer/Orders";
import View from "./View";

ReactDOM.render((
        <BrowserRouter>
            <Routes> {/* The Switch decides which component to show based on the current URL.*/}
                <Route path='/' element={<App/>}/>
                <Route path='/signup' element={<SignUp/>}/>
                <Route path='/view' element={<View/>}/>
                <Route path='/cart' element={<Cart/>}/>
                <Route path='/orders' element={<Orders/>}/>
            </Routes>
        </BrowserRouter>
    ), document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
