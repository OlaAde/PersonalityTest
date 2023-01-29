import React from 'react';
import logo from '../assets/logo.svg';
import Question from "../components/question";

const Quiz = () => {
    return (
        <div className={"flex min-h-screen flex-col items-center justify-center py-2"}>
            <main className="flex w-full flex-1 flex-col items-center justify-center px-20 text-center">
                <h1 className="text-6xl font-bold mb-6">
                    The Personality Test Calculator
                </h1>

                <Question id={1} total={5}/>
            </main>
            <footer className="flex h-24 w-full items-center justify-center border-t">
                <a
                    className="flex items-center justify-center gap-2"
                    href="https://vercel.com?utm_source=create-next-app&utm_medium=default-template&utm_campaign=create-next-app"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Powered by{' '}
                    <img src={logo} alt="Vercel Logo" width={72} height={16}/>
                </a>
            </footer>
        </div>
    );
};

export default Quiz;