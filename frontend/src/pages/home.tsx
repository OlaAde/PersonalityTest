import {Link} from "react-router-dom";
import Footer from "../components/footer";

const Home = () => {
    return (
        <div className="flex min-h-screen flex-col items-center justify-center py-2">

            <main className="flex w-full flex-1 flex-col items-center justify-center px-20 text-center">
                <h1 className="text-6xl font-bold">
                    Welcome to{' '}
                    <a className="text-blue-600" href="https://nextjs.org">
                        The Personality Test Calculator
                    </a>
                </h1>

                <p className="mt-3 text-2xl">
                    Get started by clicking the button below
                </p>

                <div className="mt-6 flex max-w-4xl flex-wrap items-center justify-around sm:w-full">
                    <Link
                        to={'/quiz'}
                        className="mt-6 w-96 rounded-xl border p-6 text-left hover:text-blue-600 focus:text-blue-600"
                    >
                        <h3 className="text-2xl font-bold">Take test &rarr;</h3>
                        <p className="mt-4 text-xl">
                            Find out if you're an extrovert or an introvert
                        </p>
                    </Link>
                </div>
            </main>
            <Footer/>

        </div>
    )
}

export default Home
