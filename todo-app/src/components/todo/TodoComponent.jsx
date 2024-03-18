import React, { useEffect, useState, useRef } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { retrieveTodoApi, updateTodoApi, createTodoApi } from './api/TodoApiService';
import { useAuth } from './security/AuthContext';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import moment from 'moment';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPencilAlt, faCalendarAlt, faStar, faCommentDots, faTag, faCheckCircle, faTimesCircle, faPaperclip } from '@fortawesome/free-solid-svg-icons';
import './css/TodoComponent.css'

const MIN_DESCRIPTION_LENGTH = 5;

export default function TodoComponent() {
    const { id } = useParams();
    const navigate = useNavigate();
    const { username } = useAuth();
    const fileInputRef = useRef(null);
    


    const [formData, setFormData] = useState({
        description: '',
        targetDate: '',
        priority: '',
        comments: '',
        tags: '',
        attachmentData: null,
        done: false
    });

    useEffect(() => {
        if (id !== "-1") {
            retrieveTodoApi(username, id)
                .then(response => {
                    setFormData(prevState => ({
                        ...prevState,
                        description: response.data.description,
                        targetDate: response.data.targetDate,
                        priority: response.data.priority,
                        comments: response.data.comments,
                        tags: response.data.tags,
                        attachmentData: response.data.attachmentData,
                        done: response.data.done  

                    }));
                })
                .catch(error => {
                    console.log(error);
                });
        }
    }, [id, username]);
    
    function removeFile() {
        setFormData(prevState => ({
            ...prevState,
            attachmentData: null,
        }));
        fileInputRef.current.value = ""; // Clear the file input
    }

    function onSubmit(values) {
        const todo = {
            id: id,
            username: username,
            description: values.description,
            targetDate: values.targetDate,
            priority: values.priority,
            comments: values.comments,
            tags: values.tags,
            attachmentData: values.attachmentData,
            
            done: values.done
        };

        if (id === "-1") {
            createTodoApi(username, todo)
                .then(response => {
                    navigate('/todos');
                })
                .catch(error => console.log(error));
        } else {
            updateTodoApi(username, id, todo)
                .then(response => {
                    navigate('/todos');
                })
                .catch(error => console.log(error));
        }
    }

    function goBack() {
        navigate(-1); // Go back to the previous route.
    }

    function handleFileChange(event) {
        event.preventDefault();
        console.log("Handling file change");
    
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                console.log("File read completed");
                setFormData(prevState => ({
                    ...prevState,
                    attachmentData: reader.result.split(",")[1] ,
                }));
            };
            reader.readAsDataURL(file);
        }
    }
    


    function validate(values) {
        let errors = {};

        if (values.description.length < 5) {
            errors.description = 'Enter at least 5 characters';
        }

        if (!values.targetDate || !moment(values.targetDate).isValid()) {
            errors.targetDate = 'Enter a valid target date';
        }

        return errors;
    }
    return (
        <div className="container funky-form-container">
            <div className="navigation">
            <div onClick={goBack} className="back-link">
                🔙 Go Back
            </div>
        </div>

            <h1 className="funky-heading text-center mb-4">Todo Details 🎉</h1>
            <div className="todo-form funky-form">
                <Formik
                    initialValues={formData}
                    enableReinitialize={true}
                    onSubmit={onSubmit}
                    validate={validate}
                    validateOnChange={false}
                    validateOnBlur={false}
                >
                    {props => (
                        <Form>
                            <ErrorMessage name="description" component="div" className="alert alert-warning" />
                            <ErrorMessage name="targetDate" component="div" className="alert alert-warning" />

                            <div className="form-group funky-group">
        <label><FontAwesomeIcon icon={faPaperclip} /> Attachment</label>
        <input 
            type="file" 
            className="funky-input" 
            onChange={handleFileChange} 
            name="attachments" 
            ref={fileInputRef} // Attach the ref
        />
        {formData.attachmentData && 
            <button type="button" onClick={removeFile} className="remove-btn">Remove</button>
        }
    </div>


                            <div className="form-group funky-group">
                                <label><FontAwesomeIcon icon={faPencilAlt} /> Description</label>
                                <Field type="text" className="des" name="description" />
                            </div>

                            <div className="form-group funky-group">
                                <label><FontAwesomeIcon icon={faCalendarAlt} /> Target Date</label>
                                <Field type="date" className="tar" name="targetDate" />
                            </div>

                            <div className="form-group funky-group">
                                <label>Is Done?</label>
                                <Field type="checkbox" className="don" name="done" />
                            </div>

                            <div className="form-group funky-group">
                                <label><FontAwesomeIcon icon={faStar} /> Priority</label>
                                <Field as="select" className="pri" name="priority">
                                    <option >Select from below</option>
                                    <option value="Priority 1">Priority 1</option>
                                    <option value="Priority 2">Priority 2</option>
                                    <option value="Priority 3">Priority 3</option>
                                    <option value="Priority 4">Priority 4</option>
                                    </Field>
                            </div>

                           

                            <div className="form-group funky-group">
                                <label><FontAwesomeIcon icon={faCommentDots} /> Comments</label>
                                <Field type="text" className="com" name="comments" />
                            </div>

                            

                            <div className="form-group funky-group">
                                <label><FontAwesomeIcon icon={faTag} /> Tags</label>
                                <Field type="text" className="tag" name="tags" />
                            </div>

                           

                            <div className="text-center mt-4 funky-btn-container">
                <button  className="sub" type="submit">Save</button>
                
            </div>
                        </Form>
                    )}
                </Formik>
            </div>
        </div>
    );
}