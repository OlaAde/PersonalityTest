import React, {useEffect, useState} from 'react';
import Question from "../components/question";
import {QuestionType} from "../types/question";
import Footer from "../components/footer";

const Quiz = () => {
    const [questions, setQuestions] = useState<QuestionType[]>([]);
    const [current, setCurrent] = useState(0);


    useEffect(() => {
        fetch('/api/questions')
            .then(response => response.json())
            .then(questions => setQuestions(questions))
            .catch(error => console.error(error))
            .finally(() => {
            });
    }, []);

    function onNext() {
        if (current === questions.length - 1) {
            onFinish()
        } else {
            setCurrent(current => current + 1);
        }
    }

    function onPrevious() {
        if (current === 0) {
            return;
        }
        setCurrent(current => current - 1);
    }

    function onFinish() {

    }

    return (
        <main className="antialiased text-gray-700 bg-gray-100">
            <div id="app" className="flex w-full h-screen justify-center items-center">
                <div className="w-full max-w-3xl p-3">
                    <h1 className="font-bold text-5xl text-center text-indigo-700">
                        The Personality Test Calculator
                    </h1>
                    {questions.length > current &&
                        <Question current={current} total={questions.length} question={questions[current]}
                                  onNext={onNext} onPrevious={onPrevious}
                                  isFirst={current === 0}
                                  isLast={questions.length - 1 === current}
                        />}
                </div>
            </div>

            <Footer/>
        </main>
    );
};

export default Quiz;