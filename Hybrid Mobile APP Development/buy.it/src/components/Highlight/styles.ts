import styled from 'styled-components/native';

export const Container = styled.View`
  width: 100%;
  margin: 30px 0;
  padding: 0 30px;
`;

export const Subtitle = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.REGULAR};
  color: ${({ theme }) => theme.COLORS.GRAY_300};
`;

export const Flex = styled.View`
  flex-direction: row;
  gap: 8px;
`;

export const Title = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.XL}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

export const HighlightedText = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.XL}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  color: ${({ theme }) => theme.COLORS.PRIMARY_LIGHT};
`;
