import { css } from 'styled-components';
import styled from 'styled-components/native';

interface Props {
  size: 'SM' | 'MD' | 'LG' | 'XL' | 'XXL';
  type: 'primary' | 'secondary';
  bottom?: boolean;
  backgroundColor?: string;
  iconFirst?: boolean;
}

export const ButtonContainer = styled.TouchableOpacity<Props>`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap: 20px;
  align-self: ${({ size }) => (size === 'SM' ? 'flex-start' : 'default')};

  background-color: ${({ backgroundColor }) => backgroundColor};

  border-radius: 8px;
  border-bottom-right-radius: 8px;
  border-bottom-left-radius: 8px;

  padding: ${({ size }) => (size === 'SM' ? '8px 12px' : '16px')};

  opacity: ${({ disabled }) => (disabled ? 0.6 : 1)};

  ${props =>
    props.bottom &&
    css`
      position: absolute;
      bottom: 0;
      width: 100%;
      border-bottom-right-radius: 0;
      border-bottom-left-radius: 0;
    `}

  ${props =>
    props.type == "secondary" &&
    css`
      background: transparent;
      flex-direction: column;
      gap: 5px;
    `
  }

  ${props =>
    props.iconFirst && `flex-direction: column-reverse;`
  }
`;

export const ButtonText = styled.Text<Props>`
  font-size: ${({ theme, size }) => theme.FONT_SIZE[size]}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  text-align: center;

  color: ${({ type, theme }) =>
    type === "primary" ? theme.COLORS.WHITE : theme.COLORS.PRIMARY_LIGHTER};

  ${({ disabled, theme }) =>
    disabled &&
    css`
      color: ${theme.COLORS.GRAY_300};
    `}
`;
