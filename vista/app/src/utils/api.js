import axios from 'axios';
import {getAuthToken} from './storage';

export const BASE_URL = 'http://localhost:8080';

axios.defaults.baseURL = BASE_URL;
axios.defaults.headers.post['Content-Type'] = 'application/json';

export const config = () => ({headers:{Authorization: getAuthToken()}})

export const _post   = axios.post;
export const _get    = axios.get;
export const _put    = axios.put;
export const _delete = axios.delete;
