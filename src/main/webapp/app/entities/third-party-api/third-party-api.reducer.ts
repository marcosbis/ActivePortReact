import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IThirdPartyApi, defaultValue } from 'app/shared/model/third-party-api.model';

export const ACTION_TYPES = {
  FETCH_THIRDPARTYAPI_LIST: 'thirdPartyApi/FETCH_THIRDPARTYAPI_LIST',
  FETCH_THIRDPARTYAPI: 'thirdPartyApi/FETCH_THIRDPARTYAPI',
  CREATE_THIRDPARTYAPI: 'thirdPartyApi/CREATE_THIRDPARTYAPI',
  UPDATE_THIRDPARTYAPI: 'thirdPartyApi/UPDATE_THIRDPARTYAPI',
  DELETE_THIRDPARTYAPI: 'thirdPartyApi/DELETE_THIRDPARTYAPI',
  RESET: 'thirdPartyApi/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IThirdPartyApi>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ThirdPartyApiState = Readonly<typeof initialState>;

// Reducer

export default (state: ThirdPartyApiState = initialState, action): ThirdPartyApiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_THIRDPARTYAPI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_THIRDPARTYAPI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_THIRDPARTYAPI):
    case REQUEST(ACTION_TYPES.UPDATE_THIRDPARTYAPI):
    case REQUEST(ACTION_TYPES.DELETE_THIRDPARTYAPI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_THIRDPARTYAPI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_THIRDPARTYAPI):
    case FAILURE(ACTION_TYPES.CREATE_THIRDPARTYAPI):
    case FAILURE(ACTION_TYPES.UPDATE_THIRDPARTYAPI):
    case FAILURE(ACTION_TYPES.DELETE_THIRDPARTYAPI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_THIRDPARTYAPI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_THIRDPARTYAPI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_THIRDPARTYAPI):
    case SUCCESS(ACTION_TYPES.UPDATE_THIRDPARTYAPI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_THIRDPARTYAPI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/third-party-apis';

// Actions

export const getEntities: ICrudGetAllAction<IThirdPartyApi> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_THIRDPARTYAPI_LIST,
    payload: axios.get<IThirdPartyApi>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IThirdPartyApi> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_THIRDPARTYAPI,
    payload: axios.get<IThirdPartyApi>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IThirdPartyApi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_THIRDPARTYAPI,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IThirdPartyApi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_THIRDPARTYAPI,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IThirdPartyApi> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_THIRDPARTYAPI,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
