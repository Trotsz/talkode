function Page404() {
  return (
    <main className="h-screen w-full flex flex-col justify-center items-center bg-purple-3">
      <h1 className="md:text-[200px] text-[150px] font-extrabold text-white tracking-widest">
        404
      </h1>
      <div className="md:text-[20px] text-[18px] bg-purple-1 px-2 text-white rounded absolute font-oswald uppercase mb-8 mr-4">
        Page not found
      </div>
      <a href="/">
        <button className="flex-1 rounded-l-2xl h-10 p-2 text-center my-2 lg:my-5 button-primary">
          <span className="button-content">Go back</span>
        </button>
      </a>
    </main>
  );
}

export default Page404;
