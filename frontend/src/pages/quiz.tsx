import React, {useEffect, useState} from 'react';
import Question from "../components/question";
import {AnswerType, QuestionType} from "../types/question";
import Footer from "../components/footer";
import {useNavigate} from "react-router-dom";

const Quiz = () => {
    const [questions, setQuestions] = useState<QuestionType[]>([]);
    const [current, setCurrent] = useState(0);
    const [selections, setSelections] = useState<{ [id: number]: AnswerType }>({});
    const navigate = useNavigate();

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

    function onSelectAnswer(answer: AnswerType) {
        setSelections(selections => ({...selections, [current]: answer}));
    }

    function checkIsIntrovert() {
        const totalScore = Object.values(selections).map(answer => answer.answerWeight)
            .reduce((acc, cur) => acc + cur, 0);

        const maxScoreForIntrovert = 80;

        return totalScore <= maxScoreForIntrovert;
    }

    function onFinish() {
        const isIntrovert = checkIsIntrovert();
        localStorage.setItem("result", isIntrovert ? "Introvert" : "Extrovert");
        navigate('/result');
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
                                  onSelectAnswer={onSelectAnswer}
                                  selectedAnswer={selections[current]}
                        />}
                </div>
            </div>

            <Footer/>
        </main>
    );
};

export default Quiz;