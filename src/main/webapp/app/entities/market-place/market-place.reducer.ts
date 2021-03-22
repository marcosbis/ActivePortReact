import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMarketPlace, defaultValue } from 'app/shared/model/market-place.model';

export const ACTION_TYPES = {
  FETCH_MARKETPLACE_LIST: 'marketPlace/FETCH_MARKETPLACE_LIST',
  FETCH_MARKETPLACE: 'marketPlace/FETCH_MARKETPLACE',
  CREATE_MARKETPLACE: 'marketPlace/CREATE_MARKETPLACE',
  UPDATE_MARKETPLACE: 'marketPlace/UPDATE_MARKETPLACE',
  DELETE_MARKETPLACE: 'marketPlace/DELETE_MARKETPLACE',
  RESET: 'marketPlace/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMarketPlace>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type MarketPlaceState = Readonly<typeof initialState>;

// Reducer

export default (state: MarketPlaceState = initialState, action): MarketPlaceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MARKETPLACE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MARKETPLACE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MARKETPLACE):
    case REQUEST(ACTION_TYPES.UPDATE_MARKETPLACE):
    case REQUEST(ACTION_TYPES.DELETE_MARKETPLACE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MARKETPLACE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MARKETPLACE):
    case FAILURE(ACTION_TYPES.CREATE_MARKETPLACE):
    case FAILURE(ACTION_TYPES.UPDATE_MARKETPLACE):
    case FAILURE(ACTION_TYPES.DELETE_MARKETPLACE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MARKETPLACE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_MARKETPLACE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MARKETPLACE):
    case SUCCESS(ACTION_TYPES.UPDATE_MARKETPLACE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MARKETPLACE):
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

const apiUrl = 'api/market-places';

// Actions

export const getEntities: ICrudGetAllAction<IMarketPlace> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_MARKETPLACE_LIST,
    payload: axios.get<IMarketPlace>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IMarketPlace> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MARKETPLACE,
    payload: axios.get<IMarketPlace>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMarketPlace> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MARKETPLACE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMarketPlace> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MARKETPLACE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMarketPlace> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MARKETPLACE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
