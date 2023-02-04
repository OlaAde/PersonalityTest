import React from 'react';
import {Link} from "react-router-dom";

type SuccessfulQuizResultProps = React.ComponentPropsWithoutRef<"div"> & {
    result: string
}

const SuccessfulQuizResult = ({result}: SuccessfulQuizResultProps) => {
    const introvertExplanation = 'Introversion is a personality trait. Introverted people tend to be more focused on internal feelings. They are usually quiet, reserved, and reflective.';
    const extrovertExplanation = 'Extroversion is a personality trait typically characterized by outgoingness, high energy, and/or talkativeness. In general, the term refers to a state of being where someone “recharges,” or draws energy, from being with other people; the opposite—drawing energy from being alone—is known as introversion.';
    return (
        <div className="flex w-full flex-1 flex-col items-center justify-center px-20 text-center">
            <h1 className="text-6xl font-bold">
                You are an {' '}
                <a className="text-blue-600" href="https://nextjs.org">
                    {result}
                </a>
            </h1>

            <p className="mt-3 text-2xl w-full max-w-3xl">
                {result === 'Introvert' ? introvertExplanation : extrovertExplanation}
            </p>

            <Link
                to={'/quiz'}
                className="mt-6 w-96 rounded-xl border p-6 text-left hover:text-blue-600 focus:text-blue-600"
            >
                <h3 className="text-2xl font-bold">Retake the test &rarr;</h3>
            </Link>

        </div>
    );
};

export default SuccessfulQuizResult;