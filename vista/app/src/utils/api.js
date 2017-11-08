import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

export const {post, get, put} = axios;
