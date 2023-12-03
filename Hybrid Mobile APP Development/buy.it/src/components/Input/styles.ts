import { css } from 'styled-components';
import styled from 'styled-components/native';

export const Label = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};

  margin-bottom: 12px;
`;

export const InputContainer = styled.TextInput<{ isInvalid: boolean }>`
  background-color: ${({ theme }) => theme.COLORS.GRAY_500};
  color: ${({ theme }) => theme.COLORS.GRAY_200};

  padding: 14px 12px;

  border: 1px solid ${({ theme }) => theme.COLORS.GRAY_500};
  border-radius: 8px;

  ${props => props.isInvalid && css`
    border: 1px solid ${({ theme }) => theme.COLORS.RED};
  `};
`;

export const ErrorMessage = styled.Text`
  color: ${({ theme }) => theme.COLORS.RED};
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};

  margin: 10px 0 5px;
`;
