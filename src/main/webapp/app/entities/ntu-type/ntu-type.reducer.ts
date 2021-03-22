import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INtuType, defaultValue } from 'app/shared/model/ntu-type.model';

export const ACTION_TYPES = {
  FETCH_NTUTYPE_LIST: 'ntuType/FETCH_NTUTYPE_LIST',
  FETCH_NTUTYPE: 'ntuType/FETCH_NTUTYPE',
  CREATE_NTUTYPE: 'ntuType/CREATE_NTUTYPE',
  UPDATE_NTUTYPE: 'ntuType/UPDATE_NTUTYPE',
  DELETE_NTUTYPE: 'ntuType/DELETE_NTUTYPE',
  SET_BLOB: 'ntuType/SET_BLOB',
  RESET: 'ntuType/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INtuType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type NtuTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: NtuTypeState = initialState, action): NtuTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NTUTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NTUTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_NTUTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_NTUTYPE):
    case REQUEST(ACTION_TYPES.DELETE_NTUTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_NTUTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NTUTYPE):
    case FAILURE(ACTION_TYPES.CREATE_NTUTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_NTUTYPE):
    case FAILURE(ACTION_TYPES.DELETE_NTUTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NTUTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_NTUTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_NTUTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_NTUTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_NTUTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/ntu-types';

// Actions

export const getEntities: ICrudGetAllAction<INtuType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_NTUTYPE_LIST,
    payload: axios.get<INtuType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<INtuType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NTUTYPE,
    payload: axios.get<INtuType>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<INtuType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NTUTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INtuType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NTUTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<INtuType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NTUTYPE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
