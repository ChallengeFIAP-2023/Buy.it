import styled from 'styled-components/native';

interface Props {
  size: 'SM' | 'MD' | 'LG' | 'XL' | 'XXL';
  bottom?: boolean;
}

export const ButtonContainer = styled.TouchableOpacity<Props>`
  background-color: ${({ theme }) => theme.COLORS.PRIMARY};
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap: 20px;
  border-radius: 8px;
  border-bottom-right-radius: ${({ bottom }) => (bottom ? '0' : '8px')};
  border-bottom-left-radius: ${({ bottom }) => (bottom ? '0' : '8px')};
  padding: ${({ size }) => (size === 'SM' ? '8px 12px' : '16px')};
  align-self: ${({ size }) => (size === 'SM' ? 'flex-start' : 'default')};
`;

export const ButtonText = styled.Text<Props>`
  font-size: ${({ theme, size }) => theme.FONT_SIZE[size]}px;
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  text-align: center;
`;
