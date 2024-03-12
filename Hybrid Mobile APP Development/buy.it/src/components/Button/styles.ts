import { css } from 'styled-components';
import styled from 'styled-components/native';

interface Props {
  size: 'SM' | 'MD' | 'LG' | 'XL' | 'XXL';
  bottom?: boolean;
  backgroundColor?: string;
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
  border-bottom-right-radius: ${({ bottom }) => (bottom ? '0' : '8px')};
  border-bottom-left-radius: ${({ bottom }) => (bottom ? '0' : '8px')};

  padding: ${({ size }) => (size === 'SM' ? '8px 12px' : '16px')};

  opacity: ${({ disabled }) => (disabled ? 0.5 : 1)};
`;

export const ButtonText = styled.Text<Props>`
  font-size: ${({ theme, size }) => theme.FONT_SIZE[size]}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  text-align: center;

  color: ${({ disabled, theme }) =>
    disabled ? theme.COLORS.GRAY_300 : theme.COLORS.WHITE};
`;
