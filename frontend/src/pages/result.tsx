import React from 'react';
import Footer from "../components/footer";
import SuccessfulQuizResult from "../components/successful-quiz-result";
import QuizResultNotFound from "../components/quiz-result-not-found";

const Result = () => {
    const result = localStorage.getItem("result");


    return (
        <div className="flex min-h-screen flex-col items-center justify-center py-2">
            {result ? <SuccessfulQuizResult result={result}/> : <QuizResultNotFound/>}
            <Footer/>
        </div>
    );
};


export default Result;