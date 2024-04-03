import styled from 'styled-components/native';

export const Container = styled.View`
  color: ${({ theme }) => theme.COLORS.WHITE};
  background-color: ${({ theme }) => theme.COLORS.GRAY_600};
  border-radius: 8px;
  padding: 20px;
`;

export const Header = styled.View`
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
`;

export const Title = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.XL}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  margin-bottom: 12px;
`;

export const Subtitle = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  margin-bottom: 20px;
`;
