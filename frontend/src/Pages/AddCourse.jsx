import { useState } from "react";

export default function AddCourse() {
    let [courseData, setCourse] = useState([]);

    const handleSubmit = (event) => {
        event.preventDefault();
        let addCourseData = {
            CourseTitle: document.getElementById("CourseTitle").value,
            CourseCode: document.getElementById("CourseCode").value,
            CourseDesc: document.getElementById("CourseDesc").value,
        };

        setCourse([...courseData, addCourseData]);
    };

    console.log(courseData);
    return (
        <div className="container-fluid text-center">
            <div className="row justify-content-center">
                <form>
                    <div className="mb-3">
                        <input
                            type="text"
                            className="form-control"
                            id="CourseTitle"
                            aria-describedby="CourseTitle"
                            placeholder="Course Title"
                        />
                    </div>
                    <div className="mb-3">
                        <input
                            type="text"
                            className="form-control"
                            id="CourseCode"
                            aria-describedby="CourseCode"
                            placeholder="Course Code"
                        />
                    </div>
                    <div className="mb-3">
                        <input
                            type="text"
                            className="form-control"
                            id="CourseDesc"
                            aria-describedby="CourseDesc"
                            placeholder="Course Description"
                        />
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={handleSubmit}>
                        Add Course
                    </button>
                </form>
            </div>
        </div>
    );
}
