import React from 'react';
import {AnswerType, QuestionType} from "../types/question";
import Answer from "./answer";

type QuestionProps = React.ComponentPropsWithoutRef<"div"> & {
    current: number,
    total: number,
    question: QuestionType,
    onPrevious: () => void,
    onNext: () => void,
    onSelectAnswer: (answer: AnswerType) => void,
    selectedAnswer: AnswerType,
    isFirst: boolean,
    isLast: boolean
}

const Question = ({
                      question,
                      onPrevious,
                      onNext,
                      isFirst,
                      isLast,
                      onSelectAnswer,
                      selectedAnswer,
                      ...props
                  }: QuestionProps) => {

    return (
        <div className="bg-white p-12 rounded-lg shadow-lg w-full mt-8" {...props}>
            <div>
                <p className="text-2xl font-bold">{question.question}</p>
                {question.answers.map(answer => <Answer answer={answer} key={answer.id}
                                                        onSelectAnswer={() => onSelectAnswer(answer)}
                                                        isSelectedAnswer={selectedAnswer?.answer === answer.answer}/>)}
                <div className="mt-6 flow-root">
                    <div className={"float-right"}>
                        <button
                            disabled={isFirst}
                            onClick={onPrevious}
                            className="bg-indigo-600 disabled:bg-indigo-400 text-white text-sm font-bold tracking-wide rounded-full px-5 py-2 mr-2 w-32">
                            Previous
                        </button>

                        <button
                            onClick={onNext}
                            disabled={!selectedAnswer}
                            className="bg-indigo-600 disabled:bg-indigo-400 text-white text-sm font-bold tracking-wide rounded-full px-5 py-2 w-32">
                            {isLast ? 'Finish' : 'Next'}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Question;