import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IXeroAccount, defaultValue } from 'app/shared/model/xero-account.model';

export const ACTION_TYPES = {
  FETCH_XEROACCOUNT_LIST: 'xeroAccount/FETCH_XEROACCOUNT_LIST',
  FETCH_XEROACCOUNT: 'xeroAccount/FETCH_XEROACCOUNT',
  CREATE_XEROACCOUNT: 'xeroAccount/CREATE_XEROACCOUNT',
  UPDATE_XEROACCOUNT: 'xeroAccount/UPDATE_XEROACCOUNT',
  DELETE_XEROACCOUNT: 'xeroAccount/DELETE_XEROACCOUNT',
  RESET: 'xeroAccount/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IXeroAccount>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type XeroAccountState = Readonly<typeof initialState>;

// Reducer

export default (state: XeroAccountState = initialState, action): XeroAccountState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_XEROACCOUNT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_XEROACCOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_XEROACCOUNT):
    case REQUEST(ACTION_TYPES.UPDATE_XEROACCOUNT):
    case REQUEST(ACTION_TYPES.DELETE_XEROACCOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_XEROACCOUNT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_XEROACCOUNT):
    case FAILURE(ACTION_TYPES.CREATE_XEROACCOUNT):
    case FAILURE(ACTION_TYPES.UPDATE_XEROACCOUNT):
    case FAILURE(ACTION_TYPES.DELETE_XEROACCOUNT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_XEROACCOUNT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_XEROACCOUNT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_XEROACCOUNT):
    case SUCCESS(ACTION_TYPES.UPDATE_XEROACCOUNT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_XEROACCOUNT):
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

const apiUrl = 'api/xero-accounts';

// Actions

export const getEntities: ICrudGetAllAction<IXeroAccount> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_XEROACCOUNT_LIST,
    payload: axios.get<IXeroAccount>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IXeroAccount> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_XEROACCOUNT,
    payload: axios.get<IXeroAccount>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IXeroAccount> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_XEROACCOUNT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IXeroAccount> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_XEROACCOUNT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IXeroAccount> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_XEROACCOUNT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
