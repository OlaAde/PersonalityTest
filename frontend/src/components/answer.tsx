import React from 'react';
import {AnswerType} from "../types/question";

type AnswerProps = React.ComponentPropsWithoutRef<"label"> & {
    answer: AnswerType
}

const Answer = ({answer, ...props}: AnswerProps) => {
    return (
        <label className="block mt-4 border border-gray-300 rounded-lg py-2 px-6 text-lg hover:bg-gray-100 cursor-pointer" {...props}>
            <input type="radio" className="hidden"/>
            {answer.answer}
        </label>
    );
};

export default Answer;