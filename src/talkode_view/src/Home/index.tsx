import { useEffect, useState } from "react";
import axiosWithAuth from "../middleware/axiosWithAuth";

interface comment {
  id: Number,
  text: String,
  post_id: Number,
  instant: Date
}

interface post {
  id: Number,
  title: String,
  text: String,
  likeCount: Number,
  commentCount: Number,
  comments: comment[],
  timestamp: Date,
  dateTime: Date
}

function Home() {
  const [Posts, setPosts] = useState<post[]>();

  async function getPosts() {
    if(Posts == null) {
      const data = await axiosWithAuth.get("http://localhost:8080/");
      const result: post[] = await data.data;

      setPosts(result);
    }
  }

  useEffect(() => {
    getPosts();
  }, []);

  return (
      <>
        <div>
          { Posts?.map((post, index)  => {
            return (
              <h2 key={index}>
                {post.title}
              </h2>
              );
          }) }
        </div>
      </>
  );
}
  
export default Home;