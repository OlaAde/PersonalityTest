import React from 'react';
import {Link} from "react-router-dom";

const QuizResultNotFound = () => {
    return (
        <main className="flex w-full flex-1 flex-col items-center justify-center px-20 text-center">
            <h1 className="text-6xl font-bold">
                It seems you have not yet taken the quiz
            </h1>

            <Link
                to={'/quiz'}
                className="mt-6 w-96 rounded-xl border p-6 text-left hover:text-blue-600 focus:text-blue-600"
            >
                <h3 className="text-2xl font-bold">Take the test &rarr;</h3>
            </Link>

        </main>
    );
};

export default QuizResultNotFound;