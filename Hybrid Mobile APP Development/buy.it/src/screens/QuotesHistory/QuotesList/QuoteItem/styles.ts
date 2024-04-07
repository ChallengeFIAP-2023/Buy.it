import { Chip } from '@components/Chip';
import styled, { css } from 'styled-components/native';

type Props = {
  contained: boolean;
}

export const Container = styled.Pressable<Props>`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  gap: 10px;

  ${props =>
    props.contained ?
    css`
      background-color: ${({ theme }) => theme.COLORS.GRAY_500};
      padding: 16px;
      margin-top: 20px;
      border-radius: 10px;
      min-height: 70px;
    `
    : 
    css`
      border-bottom-width: 1px;
      border-color: ${({ theme }) => theme.COLORS.GRAY_400};
      padding: 10px 0;
    `
  }
`;

export const QuoteTag = styled(Chip)`
  padding: 2px 10px;
`;

export const QuoteName = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

export const QuoteStatus = styled(QuoteName)`
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  font-size: ${({ theme }) => theme.FONT_SIZE.XXS}px;
  color: ${({ theme }) => theme.COLORS.GRAY_100};
`;

export const QuoteQuantity = styled(QuoteStatus)`
  font-size: ${({ theme }) => theme.FONT_SIZE.XS}px;
`;

export const QuotePrice = styled(QuoteName)`
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

export const QuotePriceSymbol = styled(QuoteName)`
  font-size: ${({ theme }) => theme.FONT_SIZE.XS}px;
`;

export const Section = styled.View<{ start?: boolean; end?: boolean }>`
  display: flex;
  flex-direction: column;
  justify-content: center;

  align-items: ${props => {
    if (props.end) return 'flex-end';
    return 'flex-start';
  }};

  ${props => props.start && "flex: 2;" }

  gap: 10px;
`;

export const QuoteIconContainer = styled.View`
  background-color: ${({ theme }) => theme.COLORS.GRAY_600};

  width: 45px;
  aspect-ratio: 1;

  border-radius: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
`;
