import React from 'react';
import {AnswerType} from "../types/question";

type AnswerProps = React.ComponentPropsWithoutRef<"label"> & {
    answer: AnswerType,
    onSelectAnswer: () => void,
    isSelectedAnswer: boolean
}

const Answer = ({answer, onSelectAnswer, isSelectedAnswer, ...props}: AnswerProps) => {
    return (
        <label
            className={`block mt-4 border border-gray-300 rounded-lg py-2 px-6 text-lg hover:bg-gray-100 cursor-pointer ${isSelectedAnswer && 'bg-gray-100 border-indigo-300'}`} {...props}>
            <input type="radio" className="hidden" onClick={onSelectAnswer} checked={isSelectedAnswer}/>
            {answer.answer}
        </label>
    );
};

export default Answer;