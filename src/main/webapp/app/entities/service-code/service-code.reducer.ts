import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IServiceCode, defaultValue } from 'app/shared/model/service-code.model';

export const ACTION_TYPES = {
  FETCH_SERVICECODE_LIST: 'serviceCode/FETCH_SERVICECODE_LIST',
  FETCH_SERVICECODE: 'serviceCode/FETCH_SERVICECODE',
  CREATE_SERVICECODE: 'serviceCode/CREATE_SERVICECODE',
  UPDATE_SERVICECODE: 'serviceCode/UPDATE_SERVICECODE',
  DELETE_SERVICECODE: 'serviceCode/DELETE_SERVICECODE',
  SET_BLOB: 'serviceCode/SET_BLOB',
  RESET: 'serviceCode/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IServiceCode>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ServiceCodeState = Readonly<typeof initialState>;

// Reducer

export default (state: ServiceCodeState = initialState, action): ServiceCodeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SERVICECODE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SERVICECODE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SERVICECODE):
    case REQUEST(ACTION_TYPES.UPDATE_SERVICECODE):
    case REQUEST(ACTION_TYPES.DELETE_SERVICECODE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SERVICECODE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SERVICECODE):
    case FAILURE(ACTION_TYPES.CREATE_SERVICECODE):
    case FAILURE(ACTION_TYPES.UPDATE_SERVICECODE):
    case FAILURE(ACTION_TYPES.DELETE_SERVICECODE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SERVICECODE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_SERVICECODE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SERVICECODE):
    case SUCCESS(ACTION_TYPES.UPDATE_SERVICECODE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SERVICECODE):
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

const apiUrl = 'api/service-codes';

// Actions

export const getEntities: ICrudGetAllAction<IServiceCode> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SERVICECODE_LIST,
    payload: axios.get<IServiceCode>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IServiceCode> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SERVICECODE,
    payload: axios.get<IServiceCode>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IServiceCode> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SERVICECODE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IServiceCode> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SERVICECODE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IServiceCode> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SERVICECODE,
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
