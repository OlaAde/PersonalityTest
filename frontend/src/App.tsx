import React from 'react';
import './App.css';
import {HashRouter, Route, Routes} from "react-router-dom";
import Home from "./pages/home";
import Quiz from "./pages/quiz";
import Result from "./pages/result";

function App() {
    return (
        <HashRouter>
            <Routes>
                <Route path={'/quiz'} element=<Quiz/>/>
                <Route path={'/result'} element=<Result/>/>
                <Route path={'/'} element=<Home/>/>
            </Routes>
        </HashRouter>
    );
}

export default App;
