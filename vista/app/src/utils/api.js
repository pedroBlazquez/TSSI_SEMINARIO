import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080';

export const {post, get, put} = axios;
