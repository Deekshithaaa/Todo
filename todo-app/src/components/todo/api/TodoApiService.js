import axios from 'axios'

const apiClient = axios.create(
    {
        baseURL: 'http://localhost:8080'
    }
);

export const retrieveAllTodosForUsernameApi
    = (username) => apiClient.get(`/users/${username}/todos`)

export const deleteTodoApi
    = (username, id) => apiClient.delete(`/users/${username}/todos/${id}`)

export const retrieveTodoApi
    = (username, id) => apiClient.get(`/users/${username}/todos/${id}`)

export const updateTodoApi
    = (username, id, todo) => apiClient.put(`/users/${username}/todos/${id}`, todo)

export const createTodoApi
    = (username,  todo) => apiClient.post(`/users/${username}/todos`, todo)

export const createUserApi 
    = (userRegistration) => apiClient.post('/users', userRegistration);

export const checkUserApi 
    = (userRegistration) => apiClient.post('/users/checkUsernameAndPassword', userRegistration);

export const retrieveTodosForTodayApi 
    = (username) => apiClient.get(`/users/${username}/todos/today`);

export const retrieveUpcomingTodosApi 
    = (username) => apiClient.get(`/users/${username}/todos/upcoming`);
    
export const retrieveTodosSortedByPriorityApi 
    = (username) => apiClient.get(`/users/${username}/todos/sorted`);
    
export const retrieveTodosByPriorityApi 
    = (username, priority) => apiClient.get(`/users/${username}/todos/priority/${priority}`);
    
export const retrieveTodosByTagApi 
    = (username, tag) => apiClient.get(`/users/${username}/todos/tags/${tag}`);
    
export const retrieveTodosByDoneApi 
    = (username) => apiClient.get(`/users/${username}/todos/done`);
    
export const retrieveTodosByNotdoneApi 
    = (username) => apiClient.get(`/users/${username}/todos/not-done`);
    
export const retrieveTodosByPendingApi 
    = (username) => apiClient.get(`/users/${username}/todos/pending-past`);

export const tagApi 
    = (username) => apiClient.get(`/users/${username}/todos/tags`);

export const checkUsernameApi
    = (userRegistration) => apiClient.post('/users/checkUsername', userRegistration);

    
    