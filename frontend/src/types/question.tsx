export type QuestionType = {
    id: string
    question: string,
    answers: AnswerType[]
}

export type AnswerType = {
    id: string,
    answer: string,
    answerWeight: number
}